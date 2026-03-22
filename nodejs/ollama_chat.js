import ollama from 'ollama';

async function chatWithModel(prompt) {
    const response = await ollama.chat({
        model: 'gemma3:4b',
        messages: [{ role: 'user', content: prompt }]
    });
    return response.message.content;
}

async function main() {
    console.log('=== Ollama Node.js 示例 ===');
    
    // 简单对话
    const result = await chatWithModel('你好，请介绍一下自己');
    console.log(`回复：${result}`);
    
    // 代码生成
    const codeResult = await chatWithModel('用 JavaScript 写一个数组去重函数');
    console.log(`\n代码生成：${codeResult}`);
    
    // 流式输出
    console.log('\n=== 流式输出 ===');
    const stream = await ollama.chat({
        model: 'gemma3:4b',
        messages: [{ role: 'user', content: '讲一个笑话' }],
        stream: true
    });
    
    for await (const chunk of stream) {
        process.stdout.write(chunk.message.content);
    }
    console.log();
}

main().catch(console.error);
